EN
Bulletin board.
An author can have several ads in different sections.
The application should allow:
1) Create:
- A heading with / without announcements.
- Announcement (name, publication date, announcement text, cost of service,
author (telephones (separate entity), address (separate entity), name, e-mail (separate entity)).
2) Edit the announcement, personal information of the author and the heading.
3) Delete the announcement / category.
4) Delete all ads of the author.
5) Show ads from one category and several categories.? (Several) IN ???
6) Filtration (author’s ads, by keyword, by date). 7777
7) Display a certain number of ads on the page (pagination).
8) Subscribe the author to ads with the specified parameters (send a specific HTML template by mail).
9) Delete inactive ad (Scheduling).
SuitableAd
To point 8, create a collection of domains with fields:
Category
String title
Price from
Price to


In the application, use Spring 5, JPA (JPQL, NamedQueries, Criteria queries).
**** Technical requirements****
The application should:
- be as densely covered with tests.
- contain a clear logical separation between presentation, logic and data source.
- assembly of the project / start of tests should be done by gradle tools.
- the finished application should be available on the bitbucket repository.
- All key points of the application logic should contain explanatory JavaDocs.
- data validation must be present.

RU
Создать приложение Доска объявлений.
Автор может иметь несколько объявлений в разных рубриках.
Приложение должно позволять:
1) Создавать: +
- Рубрику с/без объявлений.
- Объявление(название, дата публикации, текст объявления, стоимость услуги, 
						автор(телефоны(отдельная сущность), адрес(отдельная сущность), имя, э/почта(отдельная сущность)).
2) Редактировать объявление, личную информацию автора и рубрику. +
3) Удалять объявление/рубрику. +
4) Удалять все объявления автора. +
5) Показывать объявления из одной рубрики и нескольких рубрик.?(несколько) IN ???
6) Фильтрации(объявления автора, по ключевому слову, по дате). 7777
7) Выводить определенное количество объявлений на странице(pagination).
8) Подписывать автора на объявления с заданными параметрами(отправлять по почте определенный HTML шаблон).
9) Удалять неактивные объявление(Scheduling).
SuitableAd
К 8 пункту создать коллекцию доменов с полями:
Category
String title
Price from
Price to


В приложении использовать Spring 5, JPA(JPQL, NamedQueries, Criteria queries).
		      **** Технические требования****
Приложение должно:
- быть максимально плотно покрыто тестами.
- содержать четкое логическое разделение между представление, логикой и источником данных.
- cборка проекта/запуск тестов должна производится средствами gradle.
- готовое приложение должно быть доступно на bitbucket репозитории.
- все ключевые моменты логики приложения должны содержать поясняющие JavaDocs.
- обязательно должна присутствовать валидация данных.

