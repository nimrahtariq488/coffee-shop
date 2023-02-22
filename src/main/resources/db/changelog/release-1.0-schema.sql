CREATE TABLE Customer(

                      CustomerId VARCHAR(10) NOT NULL,
                      FullName VARCHAR(250) NOT NULL,
                      Email VARCHAR(250),
                      PhoneNumber VARCHAR(13),

                      PRIMARY KEY (CustomerId)
);


CREATE TABLE MenuItemCategory (

                    CategoryId VARCHAR(50) NOT NULL,
                    Description VARCHAR(250) NOT NULL,

                    PRIMARY KEY (CategoryId)
);

CREATE TABLE OrderStatus (

                            OrderStatusId VARCHAR(50) NOT NULL,
                            Description VARCHAR(250) NOT NULL,

                            PRIMARY KEY (OrderStatusId)
);

CREATE TABLE Menu (
                      MenuId INT NOT NULL AUTO_INCREMENT,
                      Name VARCHAR(50) NOT NULL,
                      StartDate DATE NOT NULL,
                      EndDate DATE NOT NULL,

                      PRIMARY KEY (MenuId),
                      UNIQUE (Name)

);

CREATE TABLE MenuItem (
                          MenuItemId INT NOT NULL AUTO_INCREMENT,
                          Name VARCHAR(50) NOT NULL,
                          Quantity INT NOT NULL,
                          Price DECIMAL(16,4),
                          CategoryId VARCHAR(50) NOT NULL,
                          MenuId INT NOT NULL,

                          PRIMARY KEY (MenuItemId),
                          FOREIGN KEY (CategoryId) REFERENCES MenuItemCategory(CategoryId),
                          FOREIGN KEY (MenuId) REFERENCES Menu(MenuId)
);

CREATE TABLE SalesOrder (
                      OrderId INT NOT NULL AUTO_INCREMENT,
                      OrderDate DATE NOT NULL,
                      CustomerId VARCHAR(10) NOT NULL,
                      TotalAmount DECIMAL(16,4),
                      OrderStatusId VARCHAR(50) NOT NULL,

                      PRIMARY KEY (OrderId),
                      FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
                      FOREIGN KEY (OrderStatusId) REFERENCES OrderStatus(OrderStatusId)

);

CREATE TABLE OrderItem (
                          OrderItemId INT NOT NULL AUTO_INCREMENT,
                          Quantity INT NOT NULL,
                          Price DECIMAL(16,4),
                          OrderId INT NOT NULL,
                          MenuItemId INT NOT NULL,

                          PRIMARY KEY (OrderItemId),
                          FOREIGN KEY (OrderId) REFERENCES SalesOrder(OrderId),
                          FOREIGN KEY (MenuItemId) REFERENCES MenuItem(MenuItemId)
);


