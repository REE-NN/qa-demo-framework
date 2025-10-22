# QA Demo Framework

Мини-фреймворк на **Java** для демонстрации подхода к автоматизации тестирования (API + UI).  
Создан с акцентом на структуру, читаемость и масштабируемость.

---

## Технологии
**Java 17**, **Maven**, **JUnit 5**, **Rest-Assured**, **Selenide**, **Allure**, **Owner**, **Jackson**

---

## Структура проекта
```
src
└── test
├── java
│ ├── api
│ │ ├── BaseApiSpec.java → общие настройки Rest-Assured
│ │ ├── clients/ → классы API-запросов
│ │ ├── models/ → POJO для JSON (Jackson)
│ │ ├── schemas/ → JSON-схемы (валидация контрактов)
│ │ └── tests/ → API-тесты (JUnit5)
│ │
│ ├── ui
│ │ ├── pages/ → Page Object (Selenide)
│ │ ├── tests/ → UI-тесты (smoke, e2e)
│ │ └── utils/ → утилиты, ожидания, генераторы
│ │
│ └── common
│ ├── config/ → Owner-конфигурации
│ └── assertions/ → кастомные проверки
│
└── resources/
└── schemas/ → JSON Schema файлы для API
```
---

## План разработки (Вариант A)

| Шаг | Этап | Статус |
|-----|------|--------|
| 1 | BaseApiSpec – общие настройки Rest-Assured | ✅ |
| 2 | Config через Owner (baseUrl, timeout) | ☐ |
| 3 | API-клиент (get/create/delete booking) | ☐ |
| 4 | Модели данных (Booking, BookingDates) | ☐ |
| 5 | CRUD-тест CreateBooking | ☐ |
| 6 | Allure – отчёты и аннотации | ☐ |
| 7 | UI-слой на Selenide (smoke) | ☐ |
| 8 | Рефакторинг, README-финализация | ☐ |

---

