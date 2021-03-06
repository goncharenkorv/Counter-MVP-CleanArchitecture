# Counter-MVP-CleanArchitecture
счетчик со звуками, вибро, сохранением/восстановлением состояния, 
построенный на чистой архитектуре (Clean Architecture) + MVP

Это приложение является продолжением разработки Счетчика https://github.com/goncharenkorv/Counter

Эта версия: 
* С озвучиванием нажатий на кнопки + вибро 
* С сохранением/восстановлением значения с помощью SharedPreferences.

Основное отличие - данное приложение построено на чистой архитектуре (Clean Architecture) + MVP (model-view-present)

С точки зрения пользователя это приложение выглядит также, как и предыдущее, 
но с точки зрения разработчика - совершенно другая архитектура:
вместо одной активити, в которой заключался весь основной код, теперь приложение разделено на уровни согласно  
Clean Architecture + MVP
См. https://proglib.io/p/clean-architecture-android-apps/

В результате, проект содержит десятки классов, реализующих архитектуру.
Это позволяет, в частности, отделить отображение от логики, обеспечить тестирование логики без анродных зависимостей.


* Без фрагментов
* Без настроек (нельзя отключить вибро или звук)

Код содержит подробный комментарий на русском языке

@author Goncharenko Ruslan
