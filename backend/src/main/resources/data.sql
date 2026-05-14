INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Armor', null
WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Armor');

INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Potion', null
WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Potion');

INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Food', null
    WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Food');

INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Medicine', null
    WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Medicine');

INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Item', null
    WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Item');

INSERT INTO categories(id, name, parent_id)
SELECT  gen_random_uuid(), 'Weapon', null
    WHERE NOT EXISTS (SELECT name FROM categories WHERE name = 'Weapon');
