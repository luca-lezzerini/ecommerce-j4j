import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaSpedizioniComponent } from './anagrafica-spedizioni.component';

describe('AnagraficaSpedizioniComponent', () => {
  let component: AnagraficaSpedizioniComponent;
  let fixture: ComponentFixture<AnagraficaSpedizioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficaSpedizioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaSpedizioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
