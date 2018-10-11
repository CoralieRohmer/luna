#!/bin/bash
cd /home/coralie/luna/
if [ -e bin ]; then
rm -r bin
fi
mkdir bin
cd source
javac Main.java
pwd
REPERTOIRES=`ls -d */|sed "s/^/bin\//g"|tr "\n" " "`
echo $REPERTOIRES

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

cd bin
jar cvmf ../META-INF/MANIFEST.MF ../Luna.jar *.class gui/*.class \
tool/*.class
cd ..

LUNA_PATH=`pwd`
chmod 777 Luna.jar
cd $HOME/Bureau
if [ -e Luna ]; then
	rm Luna
fi
ln -s $LUNA_PATH/Luna.jar Luna

 
