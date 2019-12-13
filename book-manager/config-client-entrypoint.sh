
while ! nc -z config-server 10210 ; do
    echo "Waiting for upcoming Config Server"
    sleep 2
done

java -jar book-manager-1.0-SNAPSHOT.jar