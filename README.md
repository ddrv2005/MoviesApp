# MoviesApp

MoviesApp es una aplicación psr ver las peliculas mas populares usando la API de TheMovieDB.

Puedes navegar entre la lista de peliculas y ver su detalle, ademas, puedes utiliazr el buscador y encontrar la pelicula de tu preferencia.

La arquitectura usada en la aplicación es MVVM. Para la inyección de dependencias se uso la libreria Koin y ademas Coroutines para el manejo de diferentes hilos.

Se utlizaron diversos componentes de arquitectura de Android como ViewModel y LiveData. Tenia en mente implementar room para almacenar datos en el dispositvo(No me dio tiempo de implementar). Logramos cubrir diversos Memory Leaks que pasaban en la aplicación sin embargo quedan muchas cosas por mejorar, como mostrar las series mas populares o implementar diversas listas de peliculas como: populares, las mas nuevas, entre otras.


<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMoviesSplsh.jpeg" width="250">
<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMovieHome.jpeg" width="250">
<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMoviesDetails.jpeg" width="250">
<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMovieSearch.jpeg" width="250">
<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMovieError.jpeg" width="250">
<img src="https://github.com/ddrv2005/MoviesApp/blob/develop/Screenshots/AppMovieEmpty.jpeg" width="250">
