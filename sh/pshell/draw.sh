cd ../../results/$1/splots

i=1
if [ $i -gt 0 ] ; then
	ls -t | head -n 1 | xargs cat > current.txt
	gnuplot "../../sh/pshell/basic.plt"
fi
