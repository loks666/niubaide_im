cd niubaide_im/niubaide_backend

git checkout dev

git fetch

git pull

mvn clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true

cd target

echo "==================== niubaide_im启动！！！========================="

nohup java -jar  -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 niubaide_backend-0.0.1-SNAPSHOT.jar > niubaide_im.log  2>&1 & 




