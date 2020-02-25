export interface ITitoloStudio {
  id?: number;
  tipologia?: string;
  descrizione?: string;
  conseguimento?: string;
  anno?: string;
  voto?: string;
  anagraficaId?: number;
}

export class TitoloStudio implements ITitoloStudio {
  constructor(
    public id?: number,
    public tipologia?: string,
    public descrizione?: string,
    public conseguimento?: string,
    public anno?: string,
    public voto?: string,
    public anagraficaId?: number
  ) {}
}
