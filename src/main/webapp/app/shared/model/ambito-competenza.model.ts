import { ICompetenza } from 'app/shared/model/competenza.model';

export interface IAmbitoCompetenza {
  id?: number;
  descrizione?: string;
  tipo?: string;
  competenzas?: ICompetenza[];
  sottoambitos?: IAmbitoCompetenza[];
  ambitoId?: number;
}

export class AmbitoCompetenza implements IAmbitoCompetenza {
  constructor(
    public id?: number,
    public descrizione?: string,
    public tipo?: string,
    public competenzas?: ICompetenza[],
    public sottoambitos?: IAmbitoCompetenza[],
    public ambitoId?: number
  ) {}
}
