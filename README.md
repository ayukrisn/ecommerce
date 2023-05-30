# API Java Sederhana | E-commerce
Nama: I Gusti Ayu Krisna Kusuma Dewi
NIM  : 2205551072
Kelas: PBO E

## Pengenalan
Proyek ini merupakan suatu backendAPI sederhana yang dibuat dengan Java dengan Maven, yang mana disusun untuk aplikasi e-commerce sederhana. API digunakan untuk mengakses dan melakukan manipulasi data pada tiap entitas dari database dan dapat meng-handle GET, POST, PUT, DELETE. Response yang diberikan oleh server API sendiri menggunakan format **JSON** dan data disimpan pada **SQLite**. Pengujian aplikasi dilakukan pada aplikasi **Postman**.

## Struktur Program
Program ini memiliki 3 tipe class, yakni class untuk masing-masing Entitas yang terletak pada folder **model**, class untuk keperluan API dan HTTP Server pada folder **httpserver**, dan class untuk keperluan database pada folder **persistence**.

## Test pada Postman
Program pada dasarnya dapat digunakan dengan mengakses **localhost:8072** di web browser masing-masing. Namun, untuk mempermudah test, maka digunakan Postman.

### GET
Mendapatkan record seluruh users <br/>
`http://localhost:8072/users`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/55f87308-f9d3-4883-92f2-9bd399e4071c)

Mendapatkan record dari user dengan ID 4 dan addressnya <br/>
`http://localhost:8072/users/4`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/a2e3be6a-2241-432f-8368-0ece3c472c53)

Mendapatkan record dari user dengan tipe buyer atau seller <br/>
`localhost:8072/users?type=buyer`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/39fbb2ff-125d-4665-976a-ceb0a34003d6)
`localhost:8072/users?type=seller`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/0ece3270-8787-4b2c-bb1d-513f0448ef81)

Mendapatkan data product dari user dengan id user tertentu <br/>
`http://localhost:8072/users/4/products`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/6a611932-b72f-4f4a-b632-3d1bd28bff5c)

Mendapatkan data reviews dari user dengan id user tertentu <br/>
`http://localhost:8072/users/5/reviews`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/dcfe7b0c-a6b1-4b5e-864d-8330853aa39e)

Mendapatkan data order dengan id order tertentu <br/>
`http://localhost:8072/orders/6`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/eecad991-d085-440a-b717-e5c2a34b69a8)

Mendapatkan data products <br/>
`http://localhost:8072/products`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/b29e3c9d-a1b7-4864-920c-bdbac86ba76b)

Mendapatkan data products dengan id products tertentu <br/>
`http://localhost:8072/products/1`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/79611db0-de96-404e-a665-097141f38d96)

### POST
Menambahkan data baru pada users <br/>
`http://localhost:8072/users`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/a2033fd3-07ff-4ad8-b2da-56e31a8b7ab7)

### PUT
Mengedit data yang sudah ada pada users dengan ID Tertentu<br/>
`http://localhost:8072/users/6`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/e0e53faa-6628-40cf-8c66-479b76703a47)

### DELETE
Menghapus data yang sudah ada pada users dengan ID Tertentu<br/>
`http://localhost:8072/users/6`
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/0bd712d3-98f5-40e6-b4ff-5b6671d7d8a2)

### ERROR 405
`PATCH http://localhost:8072` (Di luar GET, POST, PUT, DELETE)
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/2baf45c3-d0f1-4a91-b822-5d38bb04693e)

### ERROR 404
`http://localhost:8072/test/1` (Tidak ada entitas TEST)
![image](https://github.com/ayukrisn/ecommerce/assets/113322119/078ff0eb-5778-4010-857d-acb5d870d615)

