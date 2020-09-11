import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { AnagraficaCandidatoComponent } from './anagrafica-candidato.component';
import { AnagraficaCandidatoDetailComponent } from './anagrafica-candidato-detail.component';
import { AnagraficaCandidatoUpdateComponent } from './anagrafica-candidato-update.component';
import { AnagraficaCandidatoDeleteDialogComponent } from './anagrafica-candidato-delete-dialog.component';
import { anagraficaCandidatoRoute } from './anagrafica-candidato.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(anagraficaCandidatoRoute)],
  declarations: [
    AnagraficaCandidatoComponent,
    AnagraficaCandidatoDetailComponent,
    AnagraficaCandidatoUpdateComponent,
    AnagraficaCandidatoDeleteDialogComponent,
  ],
  entryComponents: [AnagraficaCandidatoDeleteDialogComponent],
})
export class JemoloRoosterAppAnagraficaCandidatoModule {}
