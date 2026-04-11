public class Musica {
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    public Musica(String titulo, String artista) {
        this(titulo, artista, 180, "Pop"); 
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
        this.titulo = titulo.trim();
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("O artista não pode ser nulo ou vazio.");
        }
        this.artista = artista.trim();
    }

    public int getDuracaoSegundos() { return duracaoSegundos; }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0 || duracaoSegundos >= 3600) {
            throw new IllegalArgumentException("A duração deve ser maior que 0 e menor que 3600 segundos.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        if (genero == null) throw new IllegalArgumentException("O gênero não pode ser nulo.");
        
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero.trim())) {
                this.genero = g; 
                return;
            }
        }
        throw new IllegalArgumentException("Gênero inválido. Aceitos: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica.");
    }

    public void exibir() {
        System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
            this.titulo, this.artista, this.getDuracaoFormatada(), this.genero);
    }

    public String getDuracaoFormatada() {
        int minutos = this.duracaoSegundos / 60;
        int segs = this.duracaoSegundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }

    public boolean contemTitulo(String busca) {
        if (busca == null) return false;
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        if (busca == null) return false;
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}