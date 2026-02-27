-- 코드를 작성해주세요

# A -> FE(카테고리), Python  카테고리 + 코드
# B -> C# -> code로 바로 찍기 
# C -> etc FE (카테고리) 카테고리에서 나머지 -> case? 

# FE에 대한 코드 모음
# Python
# C 코드모음

WITH FE AS (
    SELECT SUM(`CODE`) AS CODE
    FROM SKILLCODES
    WHERE CATEGORY LIKE 'Front End'
),
CC AS (
    SELECT `CODE`
    FROM SKILLCODES
    WHERE NAME LIKE 'C#'
),
PT AS (
    SELECT `CODE`
    FROM SKILLCODES
    WHERE NAME LIKE 'Python'
)


SELECT D2.GRADE, D1.ID, D1.EMAIL
FROM DEVELOPERS D1
INNER JOIN 
(SELECT DISTINCT CASE WHEN (D.SKILL_CODE & FE.CODE) <> 0 AND (D.SKILL_CODE & PT.CODE) <>0 THEN 'A'
            WHEN (D.SKILL_CODE & CC.CODE) <> 0 THEN 'B'
            WHEN (D.SKILL_CODE & FE.CODE) <> 0 THEN 'C'
            END AS 'GRADE', ID
FROM DEVELOPERS D, FE, CC, PT) AS D2 ON 
D1.ID = D2.ID
WHERE D2.GRADE IS NOT NULL
ORDER BY GRADE ASC, ID ASC





