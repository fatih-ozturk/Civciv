#!/bin/sh

echo "*********************************************************"
echo "Running git pre-push hook. Running Static analysis... "
echo "*********************************************************"

OUTPUT="/tmp/analysis-result"
./gradlew ktlintCheck spotlessCheck --daemon > ${OUTPUT}
EXIT_CODE=$?

if [ ${EXIT_CODE} -ne 0 ]; then
    cat ${OUTPUT}
    rm ${OUTPUT}
    echo "*********************************************"
    echo "            Static Analysis Failed           "
    echo "Please fix the above issues before pushing   "
    echo "          Run ./gradlew ktlintFormat         "
    echo "    or Run ./gradlew spotlessApply to fix    "
    echo "*********************************************"
    exit ${EXIT_CODE}
else
    rm ${OUTPUT}
    echo "*********************************************"
    echo "      Static analysis no problems found      "
    echo "*********************************************"
fi
