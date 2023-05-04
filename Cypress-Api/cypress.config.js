const { defineConfig } = require("cypress");
async function setupNodeEvents(on, config) {
  // implement node event listeners here
  return config;
}
module.exports = defineConfig({
  e2e: {
    setupNodeEvents,
    //the path of the test script will be stored as specpattern
    // specPattern: "cypress/UAT/features/*.{js,feature}",
    specPattern:
      "C:\\Users\\Administrator\\OneDrive\\Desktop\\Api-testingCypress\\API-Testing-RW\\Cypress-Api\\cypress\\integration\\*.js",
    // use this for normal
  },
});
