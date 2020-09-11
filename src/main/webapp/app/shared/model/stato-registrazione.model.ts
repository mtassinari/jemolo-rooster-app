import { ICandidato } from 'app/shared/model/candidato.model';

export interface IStatoRegistrazione {
  id?: number;
  stato?: string;
  candidatoes?: ICandidato[];
}

export class StatoRegistrazione implements IStatoRegistrazione {
  constructor(public id?: number, public stato?: string, public candidatoes?: ICandidato[]) {}
}
