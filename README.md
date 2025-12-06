# appAlumnos

Aplicación Android para la gestión de alumnos utilizando Firebase Realtime Database.

## 📋 Descripción

**appAlumnos** es una aplicación móvil desarrollada en Kotlin para Android que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre un registro de alumnos. Los datos se almacenan en Firebase Realtime Database, permitiendo sincronización en tiempo real.

## ✨ Características

- **Listado de alumnos**: Visualiza todos los alumnos registrados en un RecyclerView
- **Registro de alumnos**: Agrega nuevos alumnos con nombre y apellido
- **Edición de alumnos**: Actualiza la información de alumnos existentes
- **Eliminación de alumnos**: Elimina registros con confirmación previa
- **Sincronización en tiempo real**: Utiliza Firebase Realtime Database
- **Interfaz moderna**: Material Design con ViewBinding

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **SDK mínimo**: Android 7.0 (API 24)
- **SDK objetivo**: Android 14 (API 36)
- **Base de datos**: Firebase Realtime Database
- **Arquitectura**: MVC (Model-View-Controller)
- **UI**: Material Design Components
- **View Binding**: Activado

## 📦 Dependencias Principales

```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
implementation("com.google.firebase:firebase-analytics")
implementation("com.google.firebase:firebase-firestore")
implementation("com.google.firebase:firebase-database")

// Android
implementation("androidx.core:core-ktx")
implementation("androidx.appcompat:appcompat")
implementation("com.google.android.material:material")
implementation("androidx.constraintlayout:constraintlayout")
```

## 📱 Estructura del Proyecto

```
app/src/main/java/pe/app/appalumnos/
├── model/
│   └── Alumno.kt                    # Modelo de datos
├── controller/
│   └── AlumnoController.kt          # Lógica de negocio y operaciones Firebase
├── adapter/
│   └── AlumnosAdapter.kt            # Adaptador para RecyclerView
├── ListadoAlumnosActivity.kt        # Pantalla principal (listado)
└── RegistroAlumnosActivity.kt       # Pantalla de registro/edición
```

## 🔧 Configuración del Proyecto

### Prerrequisitos

1. Android Studio (Arctic Fox o superior)
2. JDK 11
3. Cuenta de Firebase

### Configuración de Firebase

1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Crea un nuevo proyecto o selecciona uno existente
3. Agrega una app Android con el package name: `pe.app.appalumnos`
4. Descarga el archivo `google-services.json`
5. Coloca el archivo en el directorio `app/`
6. Habilita Firebase Realtime Database en la consola de Firebase
7. Configura las reglas de seguridad:

```json
{
  "rules": {
    ".read": "now < 1767589200000",  // 2026-1-5
    ".write": "now < 1767589200000",  // 2026-1-5
  }
}
```

> ⚠️ **Nota de seguridad**: Las reglas anteriores son para desarrollo. En producción, implementa reglas de seguridad adecuadas.

### Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/Eduardo-VaZu/appAlumnos.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza el proyecto con Gradle

4. Conecta un dispositivo o inicia un emulador

5. Ejecuta la aplicación

## 📖 Uso

### Pantalla Principal (Listado)
- Visualiza todos los alumnos registrados
- Toca sobre un alumno para editarlo
- Presiona el botón flotante (+) para agregar un nuevo alumno

### Pantalla de Registro/Edición
- **Modo Nuevo**: Completa el formulario y presiona "Guardar"
- **Modo Edición**: Modifica los datos y presiona "Actualizar"
- **Eliminar**: Presiona el botón de eliminar (solo visible en modo edición)

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Model-View-Controller)**:

- **Model** (`Alumno.kt`): Define la estructura de datos
- **View** (Activities + Layouts): Interfaz de usuario
- **Controller** (`AlumnoController.kt`): Lógica de negocio y acceso a datos

## 🔐 Permisos

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 📄 Modelo de Datos

```kotlin
data class Alumno(
    var id: String = "",
    var nombre: String = "",
    var apellido: String = ""
): Serializable
```

## 🚀 Funcionalidades Futuras

- [ ] Búsqueda y filtrado de alumnos
- [ ] Agregar más campos (email, teléfono, fecha de nacimiento)
- [ ] Subir foto de perfil
- [ ] Autenticación de usuarios
- [ ] Exportar datos a PDF/Excel
- [ ] Modo offline con sincronización

## 👨‍💻 Autor

**Eduardo VaZu**
- GitHub: [@Eduardo-VaZu](https://github.com/Eduardo-VaZu)

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub
