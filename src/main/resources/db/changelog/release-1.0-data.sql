-- add some initial transactions for testing
INSERT INTO User (UserId, FullName, Email)
VALUES
('user1', 'Test user 1', 'testuser1@gmail.com'),
('user2', 'Test user 2', 'testuser2@gmail.com'),
('user3', 'Test user 3', 'testuser3@gmail.com');

INSERT INTO MenuItemCategory (CategoryId, Description)
VALUES
    ('frozenChocolate', 'Frozen Chocolate'),
    ('classic', 'Classic'),
    ('coffeeChiller', 'Coffee Chiller'),
    ('italianSoda', 'Italian Soda'),
    ('teaChiller', 'Tea Chiller');

INSERT INTO Menu (Name, StartDate, EndDate)
VALUES ('The Coffee Shop', '2022-12-31', '2024-12-31');

INSERT INTO MenuItem (Name, Quantity, Price, CategoryId, MenuId)
VALUES
('Strawberry Soda', 10, 750, 'italianSoda', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Blueberry Soda', 4, 750, 'italianSoda', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Classic Hot Chocolate', 9, 750, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Cookies & Cream Frozen Chocolate', 10, 850, 'frozenChocolate', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Cappuccino', 15, 750, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Tea Latte', 18, 850, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Espresso', 10, 850, 'classic', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Green Tea', 4, 750, 'teaChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Icepresso', 3, 850, 'coffeeChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop')),
('Chillatte', 10, 850, 'coffeeChiller', (SELECT MenuId FROM Menu WHERE Name = 'The Coffee Shop'));