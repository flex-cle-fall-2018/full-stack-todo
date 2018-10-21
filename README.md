# Full-Stack To-Do App

## Environment Setup
After cloning, set up environment and install dependencies for both frontend and backend development:
1. Create Eclipse project and install Java dependencies via Gradle: `gradle eclipse`
1. Create a directory for Node.js dependencies: `cd src/main/resources && mkdir node_modules`
1. Import the project into Eclipse.
1. Right click the `node_modules` folder. Click `Build Path -> Exclude` so Eclipse will not try to validate and work with the contents of this folder. `node_modules` will contain many files so this is an important step to avoid Eclipse slowing down for no reason.
1. From within `src/main/resources`, run `npm install` to install Node.js dependencies.

## Testing JavaScript with Jest
To run Jest tests, ensure you are in the `src/main/resources` directory and have already run `npm install` to download your Node.js dependencies.

You can run `npm test` from your `src/main/resources` directory to test your JavaScript code.
