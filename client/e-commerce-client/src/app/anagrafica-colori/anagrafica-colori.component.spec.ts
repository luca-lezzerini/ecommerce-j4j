import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaColoriComponent } from './anagrafica-colori.component';

describe('AnagraficaColoriComponent', () => {
  let component: AnagraficaColoriComponent;
  let fixture: ComponentFixture<AnagraficaColoriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficaColoriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaColoriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
