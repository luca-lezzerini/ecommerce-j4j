import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociazioniProdottiTaglieComponent } from './associazioni-prodotti-taglie.component';

describe('AssociazioniProdottiTaglieComponent', () => {
  let component: AssociazioniProdottiTaglieComponent;
  let fixture: ComponentFixture<AssociazioniProdottiTaglieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociazioniProdottiTaglieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociazioniProdottiTaglieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
