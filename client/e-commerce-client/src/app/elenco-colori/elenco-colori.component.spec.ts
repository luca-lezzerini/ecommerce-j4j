import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElencoColoriComponent } from './elenco-colori.component';

describe('ElencoColoriComponent', () => {
  let component: ElencoColoriComponent;
  let fixture: ComponentFixture<ElencoColoriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElencoColoriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElencoColoriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
