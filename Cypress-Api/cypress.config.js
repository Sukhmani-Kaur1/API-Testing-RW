const { defineConfig } = require("cypress");
async function setupNodeEvents(on, config) {
  // implement node event listeners here
  require("cypress-mochawesome-reporter/plugin")(on);
  return config;
}
module.exports = defineConfig({
  reporter: "cypress-mochawesome-reporter",
  e2e: {
    setupNodeEvents,
    specPattern:
      "C:\\Users\\Administrator\\OneDrive\\Desktop\\Api-testingCypress\\API-Testing-RW\\Cypress-Api\\cypress\\integration\\example\\*.js",
  },
});
