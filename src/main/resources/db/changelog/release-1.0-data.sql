-- add some initial transactions for testing
INSERT INTO Customer (CustomerId, FullName, Email, PhoneNumber)
VALUES
('customer1', 'Test user 1', 'testuser1@gmail.com', '+923390878909'),
('customer2', 'Test user 2', 'testuser2@gmail.com', '+923111111111'),
('customer3', 'Test user 3', 'testuser3@gmail.com', '+923334567890');


INSERT INTO MenuItemCategory (CategoryId, Description)
VALUES
    ('frozenChocolate', 'Frozen Chocolate'),
    ('classic', 'Classic'),
    ('coffeeChiller', 'Coffee Chiller'),
    ('italianSoda', 'Italian Soda'),
    ('teaChiller', 'Tea Chiller');


INSERT INTO OrderStatus (OrderStatusId, Description)
VALUES
    ('placed', 'Placed'),
    ('preparing', 'Preparing'),
    ('delivered', 'Delivered'),
    ('cancelled', 'Cancelled');


INSERT INTO Menu (Name, StartDate, EndDate)
VALUES ('The Coffee Shop', '2022-12-31', '2024-12-31');


INSERT INTO MenuItem (MenuItemId, Name, Quantity, Price, CategoryId, MenuId)
VALUES
(1, 'Strawberry Soda', 10, 750.0000, 'italianSoda', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(2, 'Blueberry Soda', 4, 750.0000, 'italianSoda', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(3, 'Classic Hot Chocolate', 9, 750.0000, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(4, 'Cookies & Cream Frozen Chocolate', 10, 850.0000, 'frozenChocolate', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(5, 'Cappuccino', 15, 750.0000, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(6, 'Tea Latte', 18, 850.0000, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(7, 'Espresso', 10, 850.0000, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(8, 'Green Tea', 4, 749.9900, 'teaChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(9, 'Icepresso', 3, 850.0000, 'coffeeChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
(10, 'Chillatte', 10, 850.0000, 'coffeeChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop'));


INSERT INTO SalesOrder (OrderDate, CustomerId, TotalAmount, OrderStatusId)
VALUES (NOW(), 'customer1', 749.9900, 'delivered'),
       ('2022-12-31', 'customer2', 850.00, 'delivered'),
       (NOW(), 'customer3', 2550.0000, 'preparing'),
       (NOW(), 'customer1', 750.0000, 'placed');


INSERT INTO OrderItem (Quantity, Price, OrderId, MenuItemId)
VALUES (1, 749.9900, (SELECT OrderId FROM SalesOrder WHERE CustomerId = 'customer3'), (SELECT MenuItemId FROM MenuItem WHERE Name = 'Green Tea')),
       (1, 850.0000, (SELECT OrderId FROM SalesOrder WHERE CustomerId = 'customer2'), (SELECT MenuItemId FROM MenuItem WHERE Name = 'Tea Latte'));

