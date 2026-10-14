# INFORME TÉCNICO Y DE MIGRACIÓN - JULES

## 1. Nombre y Enlace del Repositorio
- **Repositorio de Origen:** `suarezlopezmaykel-wq/APK-Doctor-AI`
- **Enlace:** [https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI](https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI)
- **Aclaración sobre la Creación de Repositorio Nuevo:** Los permisos del token de ejecutor dentro del entorno sandbox restringido no permiten invocar la API de creación de nuevos repositorios de GitHub (`gh repo create` / OAuth scope `public_repo` para la creación de proyectos top-level en la cuenta de usuario). La estructura completa del código, proyectos Gradle, manifiestos, assets HTML5 y workflows de Actions para `Turbo-Bluetooth-Racing-4` ha sido preparada, aislada y empaquetada dentro de la rama de trabajo para que pueda ser migrada o importada a un repositorio nuevo de manera inmediata.

## 2. Rama Exacta
- **Nombre de la Rama:** `jules-fix-turbo-racing-apk-bt-14075085924265135862`

## 3. Repositorios y Commits de Origen Examinados
- **`suarezlopezmaykel-wq/turbo-bluetooth-racing-4` (Commit `a328b61`):** Código base completo del juego (Java, Canvas HTML5 3D, curvas, IA y sockets RFCOMM de Bluetooth).
- **`suarezlopezmaykel-wq/APK-Doctor-AI` (PR #2 / Commit `c2437c7`):** Unificación de la estructura Android, workflow `.github/workflows/build.yml` y soporte para Android 13.

## 4. Versión del Juego Tomada como Base
- Se tomó la versión unificada completa de **Turbo Bluetooth Racing 4** con el motor Canvas 2D/3D, física de vehículos, IA de rivales, interfaz WebView y Sockets RFCOMM Bluetooth nativos.

## 5. Archivos Migrados y Modificados
- **`app/src/main/assets/index.html`**:
  - Corrección de la geometría de la carretera en la línea de salida: Adaptación de `getCurveOffset(y)` utilizando `dist = y - START_Y` para garantizar que la carretera esté exactamente centrada en `ROAD_X` en la salida (`y = START_Y`), alineando los 4 carriles asfaltados con las posiciones iniciales de los 4 vehículos.
  - Notificaciones en `window.onBluetoothStatus` para capturar los estados `'FAILED'` y `'DISCONNECTED'`.
  - Preservación del motor de curvas compuestas, la IA (`updateAI`), las colisiones y los controles.
- **`app/src/main/java/com/carrera/bluetooth3d/MainActivity.java`**:
  - Manejo seguro del permiso `BLUETOOTH_CONNECT` en Android 13 (API 33) en `startServer()`, `connectToDevice(...)`, `AcceptThread` y `ConnectThread`.
- **`build.gradle` y `app/build.gradle`**:
  - Configuración Groovy Gradle con AGP `8.2.2`, Gradle Wrapper `8.5`, `compileSdk = 33` y `targetSdk = 33`.
- **`.github/workflows/build.yml`**:
  - Workflow de GitHub Actions configurado para compilar `./gradlew assembleDebug` y publicar el artefacto `turbo-bluetooth-racing-a03core`.

## 6. Funciones Conservadas
- Pantalla principal y menús del juego.
- Circuito, carretera con curvas compuestas y bordes/arcenes.
- Vehículos, colisiones, física y controles táctiles.
- Inteligencia artificial de los coches rivales.
- Salida de carrera y cuenta regresiva síncrona.
- Selección de circuitos (Bosque Encantado y Desierto Rojo).
- Servidor y cliente multijugador Bluetooth mediante Sockets RFCOMM.
- Ejecución en Android WebView sin dependencia de Internet.

## 7. Corrección Geométrica de la Carretera en la Salida
- **Problema Anterior:** `getCurveOffset(y)` sumaba un desplazamiento armónico de +432px en `START_Y = 5000`, dibujando la pista desplazada horizontalmente mientras los autos aparecían sobre el césped.
- **Solución:** La curvatura ahora se calcula como función de la distancia a la salida (`dist = y - START_Y`). En `y = START_Y`, `getCurveOffset(START_Y) = 0`, situando el centro de la carretera exactamente en `ROAD_X`, envolviendo perfectamente los 4 autos en sus 4 carriles sin desplazar los vehículos.

## 8. Revisión de Bluetooth y Android 13
- Comprobación de permisos `BLUETOOTH_CONNECT`, `BLUETOOTH_SCAN` y `BLUETOOTH_ADVERTISE` en runtime antes de instanciar hilos.
- Captura de excepciones al crear o conectar Sockets con notificación a la UI para evitar cierres inesperados.

## 9. Herramientas y Entorno de Compilación
- **Java:** JDK 17 / OpenJDK 21
- **Gradle:** Gradle Wrapper 8.5
- **Android Gradle Plugin (AGP):** 8.2.2
- **Android SDK:** `compileSdk 33`, `targetSdk 33`, `minSdk 26`

## 10. Pruebas Ejecutadas y Resultados
- **Compilación Local:** `./gradlew assembleDebug` -> `BUILD SUCCESSFUL in 2s`.
- **Pruebas Unitarias:** `./gradlew test` -> `BUILD SUCCESSFUL in 2s`.
- **Prueba de Interfaz con Playwright:** Verificación visual realizada mediante captura de pantalla y video confirmando que los 4 autos están alineados en sus carriles sobre la pista en la cuenta regresiva inicial (`verification.png`).

## 11. Pruebas Pendientes
- Instalación física en Samsung Galaxy A03 Core (Android 13 Go Edition, ARMv7 32-bit).
- Conexión multijugador Bluetooth entre dos dispositivos físicos reales.

## 12. SHA del Commit y Estado del PR #2
- **Pull Request #2:** [https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI/pull/2](https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI/pull/2)
- **Enlace a Actions:** [https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI/actions](https://github.com/suarezlopezmaykel-wq/APK-Doctor-AI/actions)
- **Ruta del APK Local:** `app/build/outputs/apk/debug/app-debug.apk` (5.0 MB)
- **Artefacto de Actions:** `turbo-bluetooth-racing-a03core`
