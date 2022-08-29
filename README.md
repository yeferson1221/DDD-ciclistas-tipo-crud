# DDD-ciclistas-tipo-crud

## IMPORTANTE
    La bd solo guarda los datos temporalmente por la configuracion de los test, si desea se puede cambiar 
    (spring.jpa.hibernate.ddl-auto = none)
    tambien esta la configuracion del token y el tiempo de la sesion es de una semana
   
   
  ![image](https://user-images.githubusercontent.com/43868710/187256532-6bf636e2-9292-4a94-92eb-9fc68a885a40.png)



### Crear Usuario   
   una vez montado el proyecto  primero se debe crear el usuario con el fin de login crear el token 
   
   ![image](https://user-images.githubusercontent.com/43868710/187253658-774046f3-79a8-4bb0-b117-29da1560c5a4.png)
   
   
###  Login
   despues se auntentica  el usuario
   
   ![image](https://user-images.githubusercontent.com/43868710/187254276-4656b714-60e5-4905-9845-0b12a02725d7.png)
   
   este como respuesta nos da un token 
   
   ![image](https://user-images.githubusercontent.com/43868710/187254638-bc74b0fb-c853-4a10-9531-7115961aa217.png)
   

### validacion
   
   recordar que el token debe ser puesto en todas las peticiones del Crud ejemplo 
   
   ![image](https://user-images.githubusercontent.com/43868710/187255191-2c6abcdd-88b4-48bc-9177-365dc740d905.png)
   
   asi seria resultado de un POST con el token 

  ![image](https://user-images.githubusercontent.com/43868710/187255436-e1477bb1-7fef-474b-8510-f06f78327ee3.png)


   
