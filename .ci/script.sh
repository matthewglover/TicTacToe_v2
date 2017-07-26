#!/usr/bin/env bash

# Adds JVM crash logging
ulimit -c unlimited -S

gradle assemble
gradle check # debug flags can go here: "--debug --stacktrace"

# Print core dumps when JVM crashes.
RESULT=$?
if [[ ${RESULT} -ne 0 ]]; then
  JVMCRASH="$(find . -name "hs_err_pid*.log" -type f -print | head -n 1)"
  if [ -f "$JVMCRASH" ]; then
    echo "======= JVM Crash Log $JVMCRASH ======="
    cat "$JVMCRASH"
  fi

  CORES=''
  if [[ "${TRAVIS_OS_NAME}" == osx ]]; then
    CORES="$(find /cores/ -type f -print)"
  else
    CORES="$(find . -type f -name 'core.*' -print)"
  fi

  if [ -n "${CORES}" ]; then
    for core in ${CORES}; do
    echo "======= Core file $core ======="
    if [[ "${TRAVIS_OS_NAME}" == osx ]]; then
      lldb -Q -o "bt all" -f "$(which java)" -c "${core}"
    else
      gdb -n -batch -ex "thread apply all bt" -ex "set pagination 0" "$(which java)" -c "${core}"
    fi
  done
  fi

  exit ${RESULT}
fi