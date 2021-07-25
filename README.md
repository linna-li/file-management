file management
====================
## Web
#### Start mysql server(Use docker will be better, but in production, we should have seperate mysql server): 
  * brew install mysql@5.7
  * mysql.server start
  * CREATE USER 'file'@'localhost' IDENTIFIED BY 'file'; GRANT ALL PRIVILEGES ON * . * TO 'file'@'localhost'; FLUSH PRIVILEGES;
  * ALTER USER 'file'@'localhost' IDENTIFIED WITH mysql_native_password BY 'file';
  * create database testdb;
#### Start server:
#### Start Client: 
  * serve -s build; http://localhost:5000

## CLI
  * npm link
  * npm install
## Usage of CLI
  * cli-file -c list
  * cli-file -c delete -f 10
  * cli-file -c upload -p 003.png

