tr -d '\r' < $1 
perl -i -p -e 's/\r//' $1
sed -i -e 's/\r//' $1