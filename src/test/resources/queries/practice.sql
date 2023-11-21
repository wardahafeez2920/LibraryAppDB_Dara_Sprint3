SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1 - AA
select count(id) from users;
select count(distinct id) from users;
select * from users;

-- US02 -1 -WH
select count(*) from book_borrow
where returned_date is null ;

-- US05 -1
select bc.name, count(*)
from book_borrow bb
         inner join books b on bb.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;