import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { DichiarazioniObligatorieComponent } from './dichiarazioni-obligatorie.component';
import { DichiarazioniObligatorieDetailComponent } from './dichiarazioni-obligatorie-detail.component';
import { DichiarazioniObligatorieUpdateComponent } from './dichiarazioni-obligatorie-update.component';
import { DichiarazioniObligatorieDeleteDialogComponent } from './dichiarazioni-obligatorie-delete-dialog.component';
import { dichiarazioniObligatorieRoute } from './dichiarazioni-obligatorie.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(dichiarazioniObligatorieRoute)],
  declarations: [
    DichiarazioniObligatorieComponent,
    DichiarazioniObligatorieDetailComponent,
    DichiarazioniObligatorieUpdateComponent,
    DichiarazioniObligatorieDeleteDialogComponent
  ],
  entryComponents: [DichiarazioniObligatorieDeleteDialogComponent]
})
export class JemoloRoosterAppDichiarazioniObligatorieModule {}
