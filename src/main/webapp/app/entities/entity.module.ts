import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'candidato',
        loadChildren: () => import('./candidato/candidato.module').then(m => m.JemoloRoosterAppCandidatoModule)
      },
      {
        path: 'anagrafica-candidato',
        loadChildren: () =>
          import('./anagrafica-candidato/anagrafica-candidato.module').then(m => m.JemoloRoosterAppAnagraficaCandidatoModule)
      },
      {
        path: 'curriculum',
        loadChildren: () => import('./curriculum/curriculum.module').then(m => m.JemoloRoosterAppCurriculumModule)
      },
      {
        path: 'allegato',
        loadChildren: () => import('./allegato/allegato.module').then(m => m.JemoloRoosterAppAllegatoModule)
      },
      {
        path: 'competenza',
        loadChildren: () => import('./competenza/competenza.module').then(m => m.JemoloRoosterAppCompetenzaModule)
      },
      {
        path: 'ambito-competenza',
        loadChildren: () => import('./ambito-competenza/ambito-competenza.module').then(m => m.JemoloRoosterAppAmbitoCompetenzaModule)
      },
      {
        path: 'titolo-studio',
        loadChildren: () => import('./titolo-studio/titolo-studio.module').then(m => m.JemoloRoosterAppTitoloStudioModule)
      },
      {
        path: 'stato-registrazione',
        loadChildren: () => import('./stato-registrazione/stato-registrazione.module').then(m => m.JemoloRoosterAppStatoRegistrazioneModule)
      },
      {
        path: 'competenze-lng',
        loadChildren: () => import('./competenze-lng/competenze-lng.module').then(m => m.JemoloRoosterAppCompetenzeLngModule)
      },
      {
        path: 'lingua',
        loadChildren: () => import('./lingua/lingua.module').then(m => m.JemoloRoosterAppLinguaModule)
      },
      {
        path: 'dichiarazioni-obligatorie',
        loadChildren: () =>
          import('./dichiarazioni-obligatorie/dichiarazioni-obligatorie.module').then(m => m.JemoloRoosterAppDichiarazioniObligatorieModule)
      },
      {
        path: 'dichiarazioni',
        loadChildren: () => import('./dichiarazioni/dichiarazioni.module').then(m => m.JemoloRoosterAppDichiarazioniModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JemoloRoosterAppEntityModule {}
