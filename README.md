Соответственно сервис имеет набор следующих операций:
- Создать/Изменить/Получить/Удалить улицу
- Создать/Изменить/Получить/Удалить дом
- Создать/Изменить/Получить/Удалить квартиру
- Создать/Изменить/Получить/Удалить жителя
- Прикрепить/Открепить дом к улице
- Прикрепить/Открепить квартиру к дому
- Прикрепить/Открепить жителя к квартире
- Получить по идентификатору жителя всю иерархию адреса проживания
  Каждая операция должна быть представлена в виде endpoint'а, согласно стандарту REST
  — Микросервис communal
  Отвечает за получение стоимости КУ для жителя. Сервис имеет лишь одну операцию:
- Получить стоимость КУ по идентификатору жителя
  Стоимость КУ определяется по формуле КУ = 100 * КЖ * КК + (ПУ * 10), где:
  КЖ = 1.5, если количество жителей в квартире больше 3. Иначе КЖ = 1
  КК = 2.3, если квартира четная. Иначе КК = 1.9
  ПУ - количество домов на улице
  Полагается, что за информацией по адресу сервис будет ходить по фейну в первый сервис.
  Также полагается, что микросервисы не имеют доступа к таблицам другого микросервиса.
  Также требуется:
- Написать юнит-тестирование (микросервисо-независимое друг от друга тестирование)
- Возможность создавать докер-образ каждого микросервиса
- Возможность запуска созданного ранее образа в среде докер

Street:
Universal

House:
+ Name
+ Ref Street (nullable=true)

```
POST /house/{house_id}/street/{street_id}

1. Find house
2. Find street (service Street -> StreetDto.toModel() -> entity)
Fill Ref Street
```

Test:
1. Unit for each 
2. Unit Controller -> Repository

TestContainers

Jar -> docker

