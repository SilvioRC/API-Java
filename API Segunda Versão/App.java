import java.io.InputStream;

import java.net.URL;

import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {

		/* Outras API's dos 250 filmes
		 * https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
		 * https://alura-imdb-api.herokuapp.com/movies
		 * https://api.mocki.io/v2/549a5d8b
		 * https://alura-filmes.herokuapp.com/conteudos
		 * https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json
		 * 
		 */
		//String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		//ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();


		 /*API da NASA 
		 https://api.mocki.io/v2/549a5d8b/NASA-APOD
		 https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json
		  * 
		  */

		  ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNASA();
		  String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
		
		
		
		
		
		
		/*URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();*/
		
		var http =  new ClienteHttp();
		String json = http.buscaDados(url);
		
		/*extrair só os dados que interessam (título,  poster, classificação)
		
		var parser = new JsonParser();
		List<Map<String,String>> listaDeConteudos = parser.parse(json);
		
		System.out.println(listaDeConteudos.get(0));*/
		
		// exibir e manipular os dados
		
		List<Conteudo> conteudos = extrator.extraiConteudos(json);


		var geradora = new GeradorDeFigurinhas();
		
		for(int i= 0; i < 3; i++){

			Conteudo conteudo = conteudos.get(i);
			
            

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
	       
	      
		}
		 //System.out.println(" São " + conteudos.size() + " filmes " );
		 
		
		

	}

}

    

