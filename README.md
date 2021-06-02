## Instalando banco de dados MariaDB no docker 

Com o docker instalado e rodando na máquina, execute o comando para criar a imagem(Verificar se ja não contem o mysql rodando na máquina na mesma porta)
- docker run --name mariadb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mariadb:latest

Para verificar se foi instalado corretamente e está rodando 
- docker ps