# Clínica Veterinaria Móvil 獣医

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.23-7F52FF?style=for-the-badge&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-1.6.7-4285F4?style=for-the-badge&logo=jetpackcompose)
![API](https://img.shields.io/badge/API-26%2B-3DDC84?style=for-the-badge&logo=android)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

Aplicación Android nativa, desarrollada con Kotlin y Jetpack Compose, que permite a los usuarios agendar y gestionar citas para sus mascotas con especialistas veterinarios de forma fácil y rápida. Este proyecto fue desarrollado como caso de estudio para aplicar las mejores prácticas de la industria en arquitectura, pruebas y despliegue.

## 📸 Screenshots

| Lista de Doctores                               | Formulario de Agendamiento                     | Pantalla de Confirmación                          |
| ----------------------------------------------- | ---------------------------------------------- | ------------------------------------------------- |
| ![Pantalla Lista de Doctores](URL_A_TU_SCREENSHOT_1.png) | ![Pantalla Formulario](URL_A_TU_SCREENSHOT_2.png) | ![Pantalla Confirmación](URL_A_TU_SCREENSHOT_3.png) |

*(Nota: Reemplaza las URLs de arriba con las capturas de pantalla reales de tu aplicación).*

## ✨ Características Principales

* **Visualización de Especialistas:** Navega por una lista de doctores disponibles, viendo sus especialidades y perfiles.
* **Agendamiento de Citas:** Un flujo intuitivo para seleccionar un doctor, una fecha y un motivo para la consulta.
* **Validación de Formularios:** Verificación de datos en tiempo real para asegurar que toda la información necesaria sea correcta.
* **Gestión de Datos Local y Remota:** Sincronización de datos con un servidor remoto y persistencia local para una experiencia offline.
* **UI Moderna y Responsiva:** Interfaz de usuario limpia, desarrollada 100% con Jetpack Compose y siguiendo los lineamientos de Material 3.

## 🛠️ Tecnologías y Arquitectura

Este proyecto demuestra el uso de una pila tecnológica moderna y sigue las guías de arquitectura recomendadas por Google.

* **Core:**
    * [Kotlin](https://kotlinlang.org/): Lenguaje de programación principal.
    * [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://developer.android.com/kotlin/flow): Para la gestión de la asincronía.

* **Interfaz de Usuario (UI):**
    * [Jetpack Compose](https://developer.android.com/jetpack/compose): Toolkit de UI declarativo y moderno.
    * [Material 3](https://m3.material.io/): Para los componentes de diseño.
    * [Navigation Component](https://developer.android.com/guide/navigation): Para gestionar la navegación entre las pantallas de la app.
    * [Glide](https://github.com/bumptech/glide) / [Coil](https://coil-kt.github.io/coil/): Para la carga eficiente de imágenes.

* **Arquitectura:**
    * **MVVM (Model-View-ViewModel):** Patrón de arquitectura principal.
    * **Inyección de Dependencias con Hilt:** Para el desacoplamiento y la gestión del ciclo de vida de las dependencias.
    * **Única Fuente de Verdad (Single Source of Truth):** Usando un repositorio como intermediario.
    * **Modularidad:** (Opcional, si se implementó) Proyecto dividido en módulos por capa o funcionalidad.

* **Capa de Datos:**
    * [Retrofit](https://square.github.io/retrofit/): Para el consumo de la API REST.
    * [Room](https://developer.android.com/training/data-storage/room): Para la persistencia de datos en una base de datos local SQLite.

* **Pruebas:**
    * [JUnit](https://junit.org/junit4/): Para pruebas unitarias.
    * [MockK](https://mockk.io/): Para la simulación de dependencias en las pruebas.
    * [Compose Test](https://developer.android.com/jetpack/compose/testing): Para pruebas de UI.

## 🚀 Cómo Empezar

Sigue estos pasos para clonar y ejecutar el proyecto en tu máquina local.

### Prerrequisitos

* Android Studio Iguana | 2023.2.1 o superior.
* JDK 17 o superior.

### Instalación

1.  Clona el repositorio en tu máquina local:
    ```bash
    git clone [https://github.com/tu-usuario/clinica-veterinaria-movil.git](https://github.com/tu-usuario/clinica-veterinaria-movil.git)
    ```
2.  Abre el proyecto en Android Studio.
3.  Espera a que la sincronización de Gradle se complete.
4.  Ejecuta la aplicación en un emulador o dispositivo físico.

*(Opcional: Si tu aplicación requiere claves de API, añade una sección aquí explicando cómo y dónde deben ser configuradas, por ejemplo, en el archivo `local.properties`).*

## 📜 Licencia

Distribuido bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

---
Hecho con ❤️ para las mascotas.