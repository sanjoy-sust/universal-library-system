
while ! nc -z config-server 10210 ; do
    echo "Waiting for upcoming Config Server"
    sleep 2
done

java -jar auth-manager-0.0.1-SNAPSHOT.jar