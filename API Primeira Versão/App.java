import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
public class App {
    public static void main(String[] args) throws Exception {
		/* Outras API's
		 * https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
		 * https://alura-imdb-api.herokuapp.com/movies
		 * https://api.mocki.io/v2/549a5d8b
		 * https://alura-filmes.herokuapp.com/conteudos
		 * https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json
		 * 
		 */
		
		// fazer uma conexão HTTP e buscar os top 250 filmes
		
		String url = "https://alura-imdb-api.herokuapp.com/movies";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		
		
		//extrair só os dados que interessam (título,  poster, classificação)
		
		var parser = new JsonParser();
		List<Map<String,String>> listaDeFilmes = parser.parse(body);
		
		System.out.println(listaDeFilmes.get(0));
		
		// exibir e manipular os dados
		
		var geradora = new GeradorDeFigurinhas();
		
		for(Map<String,String> filme : listaDeFilmes){
			String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
	       
	      
		}
		 System.out.println(" São " + listaDeFilmes.size() + " filmes " );
		 
		
		

	}

}

    

