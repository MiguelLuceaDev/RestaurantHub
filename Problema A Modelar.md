## Texto de Requisitos - Sistema RestaurantHub

**Documento de Especificaciones del Cliente**

---

### Contexto del Negocio

Nuestra empresa "FoodChain Solutions" gestiona una red de restaurantes que incluye establecimientos de comida rápida (FastBurger, QuickPizza), restaurantes de comida formal (Elegance Dining, Mediterranean Palace) y servicios de delivery exclusivo (HomeEats, ExpressDelivery). Cada tipo de restaurante tiene características operativas diferentes y necesidades específicas de gestión.

### Problema Actual

Actualmente manejamos cada restaurante con sistemas independientes, lo que genera:

- Duplicación de esfuerzos en la gestión de menús
- Inconsistencias en el procesamiento de órdenes
- Dificultad para obtener métricas consolidadas
- Problemas de integración con proveedores de pago externos
- Falta de notificaciones en tiempo real sobre el estado de las órdenes

### Visión del Sistema

Necesitamos una plataforma unificada que permita:

**Gestión de Restaurantes**: El sistema debe poder crear automáticamente configuraciones específicas para cada tipo de restaurante. Los restaurantes de comida rápida necesitan configuraciones optimizadas para velocidad, los formales requieren gestión de reservas, y los de delivery necesitan integración con sistemas de geolocalización.

**Menús Dinámicos**: Los menús deben construirse de forma flexible. Por ejemplo, una pizza base puede tener múltiples variaciones (ingredientes, tamaños, masas especiales). Los platos complejos como las lasañas requieren especificación paso a paso de ingredientes y preparación. Queremos poder duplicar platos existentes para crear variaciones rápidamente.

**Procesamiento de Órdenes**: Las órdenes pasan por diferentes estados (recibida, confirmada, en preparación, lista, entregada, cancelada). Cada transición de estado debe notificar automáticamente a los clientes y personal relevante. El cálculo de precios debe adaptarse según el tipo de cliente (regular, VIP, empleado) y aplicar diferentes estrategias de descuentos.

**Integración de Pagos**: Necesitamos conectarnos con múltiples procesadores de pago (Stripe, PayPal, sistemas bancarios locales) que tienen APIs completamente diferentes. El sistema debe abstraer estas complejidades para el usuario final.

**Control de Acceso**: Los empleados tienen diferentes niveles de acceso. Los meseros solo pueden crear órdenes, los supervisores pueden modificarlas, y los gerentes tienen acceso completo a reportes y configuraciones.

**Customización de Productos**: Los clientes deben poder personalizar sus platos agregando ingredientes extra, modificando preparaciones, o eliminando componentes por alergias. Cada modificación afecta el precio final.

**Estructura Organizacional**: Los menús se organizan en categorías (Entradas, Platos Principales, Postres) y subcategorías (Pastas, Carnes, Vegetarianos). Esta estructura debe ser flexible y permitir reorganización fácil.

**Notificaciones en Tiempo Real**: Cuando una orden cambia de estado, todos los interesados (cliente, cocina, delivery) deben recibir notificaciones instantáneas. El sistema de cocina debe poder suscribirse solo a órdenes de su restaurante específico.

### Consideraciones Técnicas

El sistema debe ser escalable, mantenible y seguir principios de arquitectura limpia. Preferimos usar Spring Boot como framework base y necesitamos que sea fácil agregar nuevos tipos de restaurantes o métodos de pago en el futuro sin modificar código existente.

---

## Guía de Extracción de Requisitos

### Paso 1: Identificación de Entidades Principales

**Instrucciones**: Lee el texto y identifica los sustantivos clave que representarán clases en tu sistema.

**Huecos para completar**:

- Entidad principal del negocio: Restaurant
- Tipo de productos que se venden: Menu, Plate
- Documento que representa una compra: Order
- Personas que interactúan con el sistema: User 
- Agrupaciones lógicas de productos: Categoria y Subcategorias

### Paso 2: Identificación de Variaciones y Tipos

**Instrucciones**: Busca frases como "diferentes tipos de...", "cada tipo de..." o enumeraciones.

**Huecos para completar**:

- Tipos de restaurantes mencionados:
  1. FastFoodRestaurant
  2. FormalRestaurant
  3. DeliveryRestaurant
- Tipos de usuarios/roles:
  1. WaiterUser
  2. SupervisorUser
  3. ManagerUser
- Procesadores de pago mencionados:
  1. StripePaymentProcessor
  2. PayPalPaymentProcessor
  3. LocalBankPaymentProcessor

### Paso 3: Identificación de Procesos y Estados

**Instrucciones**: Busca secuencias de pasos o estados que cambien con el tiempo.

## Paso 3: Estados de orden mencionados

1. **Recibida**
2. **Confirmada**
3. **En preparación**
4. **Lista**
5. **Entregada## Paso 4: Patrones de Diseño
   
   **Factory Method** - ¿Qué necesita ser creado de diferentes formas?
   Respuesta: **Restaurantes** (cada tipo requiere configuraciones específicas)
   
   **Builder** - ¿Qué tiene construcción compleja paso a paso?
   Respuesta: **Platos complejos** (como lasañas con ingredientes y preparación específica)
   
   **Prototype** - ¿Qué necesita ser duplicado/clonado?
   Respuesta: **Platos existentes** (para crear variaciones rápidamente)
   
   **Adapter** - ¿Qué sistemas externos tienen interfaces diferentes?
   Respuesta: **Procesadores de pago** (Stripe, PayPal, sistemas bancarios locales)
   
   **Decorator** - ¿Qué se puede personalizar agregando características?
   Respuesta: **Productos/Platos** (ingredientes extra, modificaciones, eliminación de componentes)
   
   **Observer** - ¿Qué eventos necesitan notificar a múltiples interesados?
   Respuesta: **Cambios de estado de órdenes** (notificar cliente, cocina, delivery)
   
   **Strategy** - ¿Qué procesos tienen múltiples algoritmos alternativos?
   Respuesta: **Cálculo de precios** (estrategias de descuentos según tipo de cliente)
   
   **State** - ¿Qué cambia de comportamiento según su estado interno?
   Respuesta: **Órdenes** (comportamiento diferente según estado: recibida, en preparación, etc.)
   
   **Facade** - ¿Qué operaciones complejas necesitan ser simplificadas?
   Respuesta: **Sistema de pagos** (abstraer complejidades de múltiples procesadores)
   
   **Composite** - ¿Qué tiene estructura jerárquica de contenido similar?
   Respuesta: **Estructura de menús** (categorías y subcategorías organizadas jerárquicamente)
   
   **Proxy** - ¿Qué necesita control de acceso o intermediación?
   Respuesta: **Control de acceso de empleados** (diferentes niveles según rol)
   
   ## Paso 5: Mapeo a Clean Architecture
   
   **Domain (Entities)**:
   - **Restaurant** (con sus variaciones: FastFood, Formal, Delivery)
   - **Order** (con sus estados y transiciones)
   - **Menu/Plate** (con customizaciones y estructura jerárquica)
   - **User** (con roles y permisos)
   - **Payment** (abstracciones de dominio)
   
   **Application (Use Cases)**:
   
   - **CreateOrderUseCase** (procesamiento de órdenes)
   - **ProcessPaymentUseCase** (gestión de pagos)
   - **ManageMenuUseCase** (creación y duplicación de platos)
   - **NotifyOrderStatusUseCase** (sistema de notificaciones)
   - **CalculatePricingUseCase** (aplicación de descuentos)
   
   **Infrastructure (Adapters)**:
   
   - **PaymentProcessorAdapter** (Stripe, PayPal, bancos locales)
   - **NotificationAdapter** (sistema de notificaciones en tiempo real)
   - **DatabaseAdapter** (persistencia de datos)
   - **GeolocationAdapter** (para servicios de delivery)
   - **AuthenticationAdapter** (control de acceso y roles)**
6. **Cancelada**

##### Paso 4: Patrones de Diseño

**Factory Method** - ¿Qué necesita ser creado de diferentes formas?
Respuesta: **Restaurantes** (cada tipo requiere configuraciones específicas)

**Builder** - ¿Qué tiene construcción compleja paso a paso?
Respuesta: **Platos complejos** (como lasañas con ingredientes y preparación específica)

**Prototype** - ¿Qué necesita ser duplicado/clonado?
Respuesta: **Platos existentes** (para crear variaciones rápidamente)

**Adapter** - ¿Qué sistemas externos tienen interfaces diferentes?
Respuesta: **Procesadores de pago** (Stripe, PayPal, sistemas bancarios locales)

**Decorator** - ¿Qué se puede personalizar agregando características?
Respuesta: **Productos/Platos** (ingredientes extra, modificaciones, eliminación de componentes)

**Observer** - ¿Qué eventos necesitan notificar a múltiples interesados?
Respuesta: **Cambios de estado de órdenes** (notificar cliente, cocina, delivery)

**Strategy** - ¿Qué procesos tienen múltiples algoritmos alternativos?
Respuesta: **Cálculo de precios** (estrategias de descuentos según tipo de cliente)

**State** - ¿Qué cambia de comportamiento según su estado interno?
Respuesta: **Órdenes** (comportamiento diferente según estado: recibida, en preparación, etc.)

**Facade** - ¿Qué operaciones complejas necesitan ser simplificadas?
Respuesta: **Sistema de pagos** (abstraer complejidades de múltiples procesadores)

**Composite** - ¿Qué tiene estructura jerárquica de contenido similar?
Respuesta: **Estructura de menús** (categorías y subcategorías organizadas jerárquicamente)

**Proxy** - ¿Qué necesita control de acceso o intermediación?
Respuesta: **Control de acceso de empleados** (diferentes niveles según rol)

## Paso 5: Mapeo a Clean Architecture

**Domain (Entities)**:

- **Restaurant** (con sus variaciones: FastFood, Formal, Delivery)
- **Order** (con sus estados y transiciones)
- **Menu/Plate** (con customizaciones y estructura jerárquica)
- **User** (con roles y permisos)
- **Payment** (abstracciones de dominio)

**Application (Use Cases)**:

- **CreateOrderUseCase** (procesamiento de órdenes)
- **ProcessPaymentUseCase** (gestión de pagos)
- **ManageMenuUseCase** (creación y duplicación de platos)
- **NotifyOrderStatusUseCase** (sistema de notificaciones)
- **CalculatePricingUseCase** (aplicación de descuentos)

**Infrastructure (Adapters)**:

- **PaymentProcessorAdapter** (Stripe, PayPal, bancos locales)
- **NotificationAdapter** (sistema de notificaciones en tiempo real)
- **DatabaseAdapter** (persistencia de datos)
- **GeolocationAdapter** (para servicios de delivery)
- **AuthenticationAdapter** (control de acceso y roles)

Reintentar

[Claude puede cometer errores.  
Por favor, verifique las respuestas.](https://support.anthropic.com/en/articles/8525154-claude-is-providing-incorrect-or-misleading-responses-what-s-going-on)