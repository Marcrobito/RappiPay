# RappiPay




<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#overview">Overview</a></li>
    <li><a href="#unfinished">Unfinished</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project was built as a code challenge, using some of the more popular libraries.

The project displays a list of popular and top rated movies from [TMDB](https://developers.themoviedb.org/3/getting-started/introduction) 

### Built With
* [Jetpack Architecture Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [Gson](https://github.com/google/gson)
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [Koin](https://insert-koin.io/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- OVERVIEW -->
## Overview

We separated the app on the following modules.

### Common
Here we have some resources and classes that will be shared over the application.

### DI
Here we inject the inject dependencies to the respective modules

### Network
Will be in charge of managing the api

### Entities

### Home
In this module we present the home/main screen here we present the popular and the top rated.

I believe i should had create a separate fragment for the list and just let the main activity in charge of the navigation

### Detail
here we present the video and info of the selected item

### Search
we present a list with results accord to the selected criteria

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- UNFINISHED -->
## Unfinished
I have some problems with the time the idea was to test the viewmodels, create mock repositories 
to ensure the different possible scenarios off the response and validate that inside the view model

### Offline functionality
the offline functionality i thinks could be done using room to store the info off the list, 
and add logic to replace and delete items each time we get access to the internet 

other way could be to cache the json and use that if there's no internet contection

### Animations
Normally i implement key frame animations using multiple versions of the same layout

<p align="right">(<a href="#top">back to top</a>)</p>


