library(googleway)
key = 'AIzaSyCMNmRthMPplwBnHKyMPoPyhMC5PAfVpd8'
#Mengambil 20 data lalu mengulanginya
Restaurant1 <- google_places(search_string = "Tempat Makan, Bali", key=key)
Restaurant46 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant45$next_page_token)
Restaurant47 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant46$next_page_token)
Restaurant48 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant47$next_page_token)
Restaurant49 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant48$next_page_token)
Restaurant50 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant49$next_page_token)
Restaurant42 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant41$next_page_token)
Restaurant43 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant42$next_page_token)
Restaurant44 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant43$next_page_token)
Restaurant45 <- google_places(search_string = "Tempat Makan, Bali", 
                             key=key, 
                             page_token = Restaurant44$next_page_token)
#Save data
library(readr)
library(writexl)
path <- "E:/Folder Kuliah Semester 5/Bangkit2023/Jejaka/Bali_Restaurant"
data = Restaurant50[['results']] #ganti dataframe setelah save

#Mengambil types data
types_values <- sapply(data$types, `[`, 1)

df <- data.frame(Types = types_values)

# Join the df data frame to the original data frame
data_with_types <- cbind(data, df)

write_xlsx(data_with_types, path = paste0(path, "Restaurant_Bali50.xlsx"))
