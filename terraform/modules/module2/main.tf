provider "azurerm" {
  features {}
}

resource "random_id" "web_app_suffix" {
  byte_length = 6
}

resource "azurerm_linux_web_app" "web_app" {
  name                = "my-app-webapp-${random_id.web_app_suffix.hex}"
  location            = "France Central"
  resource_group_name = "rg-2zal1dp46k9x"
  service_plan_id     = "/subscriptions/ae17c7d1-6784-42e9-b566-e9c12a45ce71/resourceGroups/rg-2zal1dp46k9x/providers/Microsoft.Web/serverFarms/asp-2g29kk2v3ecv"

  site_config {
    application_stack {
      docker_image_name = "api:1.0.0"
      docker_registry_url = "https://acrl431lrf6sspr.azurecr.io"
      docker_registry_username = "acrl431lrf6sspr"
      docker_registry_password = "C2UHKR4izskil2aPw6HCWHzMgh97gbP9QmgzxO5qQ++ACRDjuM3N"
    }
  }

  app_settings = {
    "WEBSITES_CONTAINER_START_TIME_LIMIT" = "1800"
  }

  tags = {
    environment = "production"
  }
}

# Application Insights
resource "azurerm_application_insights" "app_insights" {
  name                = "app-insights-${random_id.web_app_suffix.hex}"
  location            = "France Central"
  resource_group_name = "rg-2zal1dp46k9x"
  application_type    = "web"
}

# Static Web App
resource "azurerm_static_web_app" "static_web_app" {
  name                = "static-web-app-${random_id.web_app_suffix.hex}"
  resource_group_name = "rg-2zal1dp46k9x"
  location            = "West Europe"

  sku_tier = "Standard"
  sku_size = "Small"

  identity {
    type = "SystemAssigned"
  }

  app_settings = {
    "REACT_APP_API_URL" = azurerm_linux_web_app.web_app.default_hostname
  }

  tags = {
    environment = "production"
  }
}