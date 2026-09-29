package model;

public class Estatisticas {
    private double totalLixo;
    private double totalAdubo;
    private double totalChorume;
    private int totalDias;
    private double mediaLixo;
    private double mediaAdubo;
    private double mediaChorume;
    private double mediaDias;

    public double getTotalLixo() { return totalLixo; }
    public void setTotalLixo(double totalLixo) { this.totalLixo = totalLixo; }

    public double getTotalAdubo() { return totalAdubo; }
    public void setTotalAdubo(double totalAdubo) { this.totalAdubo = totalAdubo; }

    public double getTotalChorume() { return totalChorume; }
    public void setTotalChorume(double totalChorume) { this.totalChorume = totalChorume; }

    public int getTotalDias() { return totalDias; }
    public void setTotalDias(int totalDias) { this.totalDias = totalDias; }

    public double getMediaLixo() { return mediaLixo; }
    public void setMediaLixo(double mediaLixo) { this.mediaLixo = mediaLixo; }

    public double getMediaAdubo() { return mediaAdubo; }
    public void setMediaAdubo(double mediaAdubo) { this.mediaAdubo = mediaAdubo; }

    public double getMediaChorume() { return mediaChorume; }
    public void setMediaChorume(double mediaChorume) { this.mediaChorume = mediaChorume; }

    public double getMediaDias() { return mediaDias; }
    public void setMediaDias(double mediaDias) { this.mediaDias = mediaDias; }
}