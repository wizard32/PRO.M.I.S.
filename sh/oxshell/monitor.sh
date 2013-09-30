#!/bin/sh
#exit 0
#Monitors the progress of phd text - original author of dds as usual
TODAY=`date +%d%h%y`
CHAPTERS=`sed -n '/NOFULLPRINT/n;s/^\\\\include{\(ch[^}]*\)}.*/\1.tex/p' PhdThesis.tex`
echo $CHAPTERS
for i in ${CHAPTERS}
do
	recode iso-8859-7..utf-8  $i
	wc -w $i | perl -ne '($words, $file) = split; print "$words\t";'
	sed -n 's/%//;s/\\mychapterx{\([^}]*\)}.*/words in the chapter \1/p' $i
	recode  utf-8..iso-8859-7 $i
done >monitor.new
echo "`egrep '\\listing|\\epsfbox|\\plisting|\\drawing|\\includegraphics' ${CHAPTERS} | wc -l` figures" >>monitor.new
echo "`fgrep '\begin{tabular}' ${CHAPTERS} | wc -l` tables" >>monitor.new

(
echo Progress Summary
echo ----------------
echo
diff monitor.old monitor.new |
sed -n ' s/< \(.*\)/There were    \1/p
s/> \(.*\)/Now there are \1/p'
echo
echo Current Status
echo --------------
echo
perl -n -e 's/^\s+//; ($l, @title) = split; printf("%5d  %s\n", $l, join(" ", @title)); $s += $l; END {printf("%5d  elements total\n", $s);}' monitor.new
) > $TODAY.log

mv -f monitor.new monitor.old

