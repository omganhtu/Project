import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlbumFetcher {


    public static String fetchAlbums(String apiUrl) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            } else {
                System.out.println("Lỗi khi gọi API. Mã phản hồi: " + responseCode);
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    // Ghi dữ liệu ra file
    public static void writeToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println(" Ghi dữ liệu thành công vào file: " + filePath);
        } catch (IOException e) {
            System.out.println(" Lỗi khi ghi dữ liệu ra file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String apiUrl = "https://jsonplaceholder.typicode.com/albums";
        String filePath = "albums.json";


        String albumsData = fetchAlbums(apiUrl);


        System.out.println(" Dữ liệu albums từ API:");
        System.out.println(albumsData);


        writeToFile(albumsData, filePath);
    }
}
