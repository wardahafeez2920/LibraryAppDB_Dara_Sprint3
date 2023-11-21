SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1 - AA


-- US02 -1 -WH
select count(*) from book_borrow
where returned_date is null ;
