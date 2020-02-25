import { Moment } from 'moment';
import { ICompetenzeLng } from 'app/shared/model/competenze-lng.model';
import { ITitoloStudio } from 'app/shared/model/titolo-studio.model';
import { ICurriculum } from 'app/shared/model/curriculum.model';
import { ICompetenza } from 'app/shared/model/competenza.model';
import { IDichiarazioniObligatorie } from 'app/shared/model/dichiarazioni-obligatorie.model';

export interface IAnagraficaCandidato {
  id?: number;
  cognome?: string;
  nome?: string;
  luogoNascita?: string;
  dataNascita?: Moment;
  codiceFiscale?: string;
  professione?: string;
  partitaIva?: string;
  numeroTelefonoFisso?: string;
  numeroTelefonoCellulare?: string;
  eMail?: string;
  indirizzoPec?: string;
  indirizzoResidenza?: string;
  capResidenza?: string;
  comuneResidenza?: string;
  provinciaResidenza?: string;
  note?: string;
  candidatoId?: number;
  competenzeLngs?: ICompetenzeLng[];
  titoloStudios?: ITitoloStudio[];
  curricula?: ICurriculum[];
  competenzas?: ICompetenza[];
  dichiarazionis?: IDichiarazioniObligatorie[];
}

export class AnagraficaCandidato implements IAnagraficaCandidato {
  constructor(
    public id?: number,
    public cognome?: string,
    public nome?: string,
    public luogoNascita?: string,
    public dataNascita?: Moment,
    public codiceFiscale?: string,
    public professione?: string,
    public partitaIva?: string,
    public numeroTelefonoFisso?: string,
    public numeroTelefonoCellulare?: string,
    public eMail?: string,
    public indirizzoPec?: string,
    public indirizzoResidenza?: string,
    public capResidenza?: string,
    public comuneResidenza?: string,
    public provinciaResidenza?: string,
    public note?: string,
    public candidatoId?: number,
    public competenzeLngs?: ICompetenzeLng[],
    public titoloStudios?: ITitoloStudio[],
    public curricula?: ICurriculum[],
    public competenzas?: ICompetenza[],
    public dichiarazionis?: IDichiarazioniObligatorie[]
  ) {}
}
