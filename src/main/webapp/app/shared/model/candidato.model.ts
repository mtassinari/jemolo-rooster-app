export interface ICandidato {
  id?: number;
  nome?: string;
  cognome?: string;
  codiceFiscale?: string;
  eMail?: string;
  anagraficaCandidatoId?: number;
  statoRegistrazioneId?: number;
}

export class Candidato implements ICandidato {
  constructor(
    public id?: number,
    public nome?: string,
    public cognome?: string,
    public codiceFiscale?: string,
    public eMail?: string,
    public anagraficaCandidatoId?: number,
    public statoRegistrazioneId?: number
  ) {}
}
