#!/bin/bash
cd /home/coralie/luna/
rm -r bin
mkdir bin
cd source
javac Main.java
REPERTOIRES=`ls -d */|sed "s/^/bin\//g"|tr "\n" " "`
cd ..
mkdir $REPERTOIRES

for REP in $REPERTOIRES; do
	REP_SOURCE=`echo $REP|sed "s/bin/source/g"`
	FICHIERS=`find $REP_SOURCE -name "*.class"|tr "\n" " "`
	if [ ! `echo $FICHIERS|cut -d " " -f 1` == "" ]; then
		mv $FICHIERS ./$REP
	fi
done

FICHIERS=`ls -d source/*.class|tr "\n" " "`
if [ ! `echo $FICHIERS|cut -d " " -f 1` == "" ]; then
	mv $FICHIERS ./bin
fi
