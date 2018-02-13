## OpenAMS Web UI


### How to run ?
 - Make sure you have latest version of Node/NPM installed (https://nodejs.org/en/).
 - From **ROOT/web-ui** directory,  
     - Run **npm install** to initialize NPM tools
     - Run **npm install webpack webpack-dev-server -g** to install webpack and webpack-dev-server globally
     - Run **npm run-script start** to start webpack-dev-server
     - Browse http://HOST:8080/login.html



### Notes
 - This project uses **NPM & webpack** for dev setup.
 - Make sure you install **webpack webpack-dev-server** for development
 - Prefer **ATOM or Visual Studio Code** for Development
 - Running **npm run-script start** will automatically reload the code changes
 - Run **npm run-script build:prod** to generate production build
 - API Configurations are in **scripts/api-config.js**
