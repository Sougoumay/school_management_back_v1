provider "azurerm" {
  features {}
}

module "module_1" {
  source = "../module1"
}

resource "random_id" "web_app_suffix" {
  byte_length = 4
}

resource "azurerm_linux_web_app" "web_app" {
  name                = "my-app-webapp-${random_id.web_app_suffix.hex}"
  location            = "France central"
  resource_group_name = module.module_1.resource_group_name
  service_plan_id     = module.module_1.app_service_plan_id

  site_config {
    application_stack {
      docker_image_name = "api:1.0.0"
      docker_registry_url = "https://${module.module_1.random_names.acr_name}.azurecr.io"
    }
    always_on = false
  }

  identity {
    type = "SystemAssigned"
  }

  app_settings = {
    "WEBSITES_CONTAINER_START_TIME_LIMIT" = "1800"
  }

  tags = {
    environment = "production"
  }
}